//
//  MKPolygon.h
//  MapKit
//
//  Copyright 2010 Apple, Inc. All rights reserved.
//

#if __IPHONE_4_0 <= __IPHONE_OS_VERSION_MAX_ALLOWED

#import <Foundation/Foundation.h>

#import <MapKit/MKMultiPoint.h>
#import <MapKit/MKOverlay.h>

@interface MKPolygon : MKMultiPoint <MKOverlay> {
@package
    CLLocationCoordinate2D _centroid;
    NSArray *_interiorPolygons;
}

+ (MKPolygon *)polygonWithPoints:(MKMapPoint *)points count:(NSUInteger)count;
+ (MKPolygon *)polygonWithPoints:(MKMapPoint *)points count:(NSUInteger)count interiorPolygons:(NSArray *)interiorPolygons;

+ (MKPolygon *)polygonWithCoordinates:(CLLocationCoordinate2D *)coords count:(NSUInteger)count;
+ (MKPolygon *)polygonWithCoordinates:(CLLocationCoordinate2D *)coords count:(NSUInteger)count interiorPolygons:(NSArray *)interiorPolygons;

@property (readonly) NSArray *interiorPolygons;

@end

#endif
